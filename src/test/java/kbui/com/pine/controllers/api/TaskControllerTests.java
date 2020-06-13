package kbui.com.pine.controllers.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import kbui.com.pine.entities.task.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TaskControllerTests {
  @LocalServerPort
	private int port;

	@Autowired
  private TestRestTemplate restTemplate;

  @Test
	public void taskPostingAPI() throws Exception {
    String url = "http://localhost:" + port + "/api/tasks";
    HttpEntity<TaskEntity> request = new HttpEntity<TaskEntity>(this.createDummyTask());
    ResponseEntity<TaskEntity> task = this.restTemplate.postForEntity(url, request, TaskEntity.class);
    assertThat(task.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(task.getBody()).isExactlyInstanceOf(TaskEntity.class);
  }

  @Test
	public void taskUpdatingAPI() throws Exception {
    TaskEntity existingTask = this.persistDummyTask();
    String url = "http://localhost:" + port + "/api/tasks/" + existingTask.getId();

    TaskEntity updatingTask = this.createDummyTask();
    updatingTask.setStatus(TaskStatus.FAILED);

    HttpEntity<TaskEntity> request = new HttpEntity<TaskEntity>(this.createDummyTask());
    this.restTemplate.put(url, request);
  }

  @Test
	public void taskListReadingAPI() throws Exception {
    String url = "http://localhost:" + port + "/api/tasks";
    HttpEntity<TaskEntity> request = new HttpEntity<TaskEntity>(this.createDummyTask());
    this.restTemplate.postForEntity(url, request, TaskEntity.class);
		TaskEntity[] tasks = this.restTemplate.getForObject(url, TaskEntity[].class);
    assertThat(tasks.length).isEqualTo(1);
  }
  
  public TaskEntity createDummyTask() {
    TaskEntity entity = new TaskEntity();
    entity.setStatus(TaskStatus.TODO);
    entity.setMessages("https://njt.now.sh/");
    return entity;
  }

  public TaskEntity persistDummyTask() {
    String url = "http://localhost:" + port + "/api/tasks";
    HttpEntity<TaskEntity> request = new HttpEntity<TaskEntity>(this.createDummyTask());
    ResponseEntity<TaskEntity> task = this.restTemplate.postForEntity(url, request, TaskEntity.class);
    return task.getBody();
  }
}
