package springframework.guru.repoSearchEngine.service.preditApiService;

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

    @Override
    public void predictCommits(ArrayList<CommitCount> commits_count_weekly){
        try {
            String commits[] = new String[4];
            for(int i=0; i<4;i++){
                commits[4- i -1] = Integer.toString((commits_count_weekly.get(i).getNumCommits()));
            }
            String requestJson = String.format("{\"commits\":%s}", Arrays.toString(commits));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
            RestTemplate restTemplate = new RestTemplate();
            String request_url = "https://senior-design.herokuapp.com/predict";
            String predit_commit = restTemplate.postForObject(request_url,entity,String.class);
        }
        catch (Exception ex){
            throw ex;
        }
    }
}
