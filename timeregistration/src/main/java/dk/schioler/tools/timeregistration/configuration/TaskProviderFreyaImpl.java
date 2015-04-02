package dk.schioler.tools.timeregistration.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import dk.schioler.tools.timeregistration.TimeRegistrationException;
import dk.schioler.tools.timeregistration.model.Project;
import dk.schioler.tools.timeregistration.model.Task;

@Component("taskProvider")
public class TaskProviderFreyaImpl implements TaskProvider {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Map<String, Project> projects = new TreeMap<String, Project>();

	private String projectFile;

	@Autowired
	public TaskProviderFreyaImpl(@Value("${config.freya-export}") String projectFile) {
		super();
		if (StringUtils.isBlank(projectFile)) {
			throw new TimeRegistrationException("projectFile can not be empty!");
		}
		this.projectFile = projectFile;
		try {
			loadProjects();
		} catch (Exception e) {
			throw new TimeRegistrationException(e.getMessage(), e);
		}

	}

	private static final int PROJECT_IDX = 1;
	private static final int TASK_IDX = 2;
	private static final int ACCENTURECODE_IDX = 3;

	public void loadProjects() throws IOException {

		logger.debug("looking up projects from {}", projectFile);
		//		FileUtils.readLines(new File(projectFile), StandardCharsets.ISO_8859_1);
		InputStream is = null;
		try {
			is = TaskProviderFreyaImpl.class.getResourceAsStream(projectFile);
			List<String> projectLines = org.apache.commons.io.IOUtils.readLines(is);

			Project currentProject = null;
			for (String string : projectLines) {
				logger.debug("line={}", string);
				String[] columns = string.split(";");
				if (StringUtils.isNotBlank(columns[PROJECT_IDX]) && StringUtils.isNotBlank(columns[TASK_IDX])) {
					if (currentProject == null) {
						currentProject = new Project(columns[PROJECT_IDX].trim());
					} else {
						if (!currentProject.getProjectName().equals(columns[PROJECT_IDX].trim())) {
							currentProject = new Project(columns[PROJECT_IDX].trim());
							projects.put(currentProject.getProjectName(), currentProject);
						}
					}

					String taskName = columns[TASK_IDX].trim();
					String accCode = columns[ACCENTURECODE_IDX].trim();
					currentProject.addTask(new Task(currentProject, accCode, taskName));
				}
			}
		} finally {
			IOUtils.closeQuietly(is);
		}

		// 	;Project Name;	Task Name/Description;	ACNT Charge Code; ...

	}

	@Override
	public Map<String, Project> getProjects() {

		return projects;
	}

}
