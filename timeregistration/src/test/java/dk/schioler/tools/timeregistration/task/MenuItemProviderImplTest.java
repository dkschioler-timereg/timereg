package dk.schioler.tools.timeregistration.task;

import static org.junit.Assert.fail;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import dk.schioler.tools.timeregistration.configuration.TaskProvider;
import dk.schioler.tools.timeregistration.configuration.TaskProviderFreyaImpl;
import dk.schioler.tools.timeregistration.model.Project;
import dk.schioler.tools.timeregistration.model.Task;

public class MenuItemProviderImplTest {

	@Test
	public void test() {
		System.out.println("Starting menuprovider test");
		String projectFile = "/freya-excel-dump.csv";
		try {
			TaskProvider mip = new TaskProviderFreyaImpl(projectFile);
			Map<String, Project> tasks = mip.getProjects();
			for (Entry<String, Project> e : tasks.entrySet()) {
				Project p = e.getValue();
				System.out.println(p.getProjectName());
				Map<String, Task> tasks2 = p.getTasks();
				for (Entry<String, Task> e2 : tasks2.entrySet()) {
					Task t = e2.getValue();
					System.out.println("   " + t.getTask() + ", " + t.getAccentureCode());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
