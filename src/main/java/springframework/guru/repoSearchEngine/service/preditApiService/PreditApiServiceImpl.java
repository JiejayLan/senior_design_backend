package springframework.guru.repoSearchEngine.service.preditApiService;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springframework.guru.repoSearchEngine.dto.response.CommitCount;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class PreditApiServiceImpl implements PreditApiService{
    private String COMMITS_PREDITION_API_URL;
    private HttpHeaders headers;
    private RestTemplate restTemplate;

    public PreditApiServiceImpl(@Value("${COMMITS_PREDITION_API_URL}") String COMMITS_PREDITION_API_URL) {
        this.COMMITS_PREDITION_API_URL = COMMITS_PREDITION_API_URL;
        this.headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void predictCommits(ArrayList<CommitCount> commits_count_weekly){
        try {
            if(commits_count_weekly.size()<4)
                return;

            String requestJson = prepareRequestBody(commits_count_weekly);
            HttpEntity<String> entity = new HttpEntity<>(requestJson,headers);
            String predit_commit = restTemplate.postForObject(COMMITS_PREDITION_API_URL,entity,String.class);
            String future_week = getFutureWeek(commits_count_weekly);
            CommitCount commitCount = new CommitCount(future_week,1);
            commits_count_weekly.add(0, commitCount);
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public String prepareRequestBody(ArrayList<CommitCount> commits_count_weekly){
        String commit_count[] = new String[4];
        for(int i=0; i<4;i++){
            commit_count[4- i -1] = Integer.toString((commits_count_weekly.get(i).getNumCommits()));
        }
        String requestJson = String.format("{\"commits\":%s}", Arrays.toString(commit_count));

        return requestJson;
    }

    @Override
    public String getFutureWeek(ArrayList<CommitCount> commits_count_weekly){
        String last_week_str = commits_count_weekly.get(0).getEndOfWeek();
        DateTime last_week = new DateTime(last_week_str);
        last_week = last_week.plusWeeks(1);
        return last_week.toString().substring(0,10);
    }
}
