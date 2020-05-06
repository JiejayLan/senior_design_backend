package springframework.guru.repoSearchEngine.service.preditApiService;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONArray;
import org.json.JSONException;
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
    private final int COMMIT_DATASET_SIZE = 8;
    public PreditApiServiceImpl(@Value("${COMMITS_PREDITION_API_URL}") String COMMITS_PREDITION_API_URL) {
        this.COMMITS_PREDITION_API_URL = COMMITS_PREDITION_API_URL;
        this.headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void predictCommits(ArrayList<CommitCount> commits_count_weekly){
        try {
            if(commits_count_weekly.size() < 4 || !isActiveRepo(commits_count_weekly))
                return;
            String requestJson = prepareRequestBody(commits_count_weekly);
            HttpEntity<String> entity = new HttpEntity<>(requestJson,headers);
            String response = restTemplate.postForObject(COMMITS_PREDITION_API_URL,entity,String.class);
            JSONArray predit_commits = new JSONArray(response);
            appendFutureCommits(commits_count_weekly, predit_commits);
        }
        catch (JSONException ex){
            System.out.println("Error:"+ex.getMessage());
            throw new Error(ex.getMessage());
        }
        catch (Exception ex){
            throw ex;
        }

    }

    @Override
    public String prepareRequestBody(ArrayList<CommitCount> commits_count_weekly){

        String commit_count[] = new String[COMMIT_DATASET_SIZE];
        for(int i = 0; i < COMMIT_DATASET_SIZE; i++){
            commit_count[COMMIT_DATASET_SIZE - i -1] = Integer.toString((commits_count_weekly.get(i).getNumCommits()));
        }
        String requestJson = String.format("{\"commits\":%s}", Arrays.toString(commit_count));

        return requestJson;
    }

    @Override
    public void appendFutureCommits(ArrayList<CommitCount> commits_count_weekly, JSONArray predit_commits){
        try{
            DateTime temp_date = new DateTime(DateTimeZone.UTC);
            temp_date = temp_date.plusWeeks(1);
            for (int i = 0; i < predit_commits.length(); i++) {
                int commit = predit_commits.getInt(i);
                temp_date = temp_date.plusWeeks(1);
                CommitCount commitCount = new CommitCount(temp_date.toString().substring(0,10),commit);
                commits_count_weekly.add(0, commitCount);
            }
        }
        catch (JSONException ex){
            System.out.println("Error:"+ex.getMessage());
            throw new Error(ex.getMessage());
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public boolean isActiveRepo(ArrayList<CommitCount> commits_count_weekly){
        int sum = 0;
        for(int i = 0; i < Math.min(commits_count_weekly.size(), COMMIT_DATASET_SIZE); i++)
            sum += commits_count_weekly.get(i).getNumCommits();
        return sum > 0;
    }
}
