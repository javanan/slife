package com.slife.service;

import com.slife.base.vo.DataTable;
import com.slife.utils.ActivitiUtils;
import com.slife.vo.ProcessVO;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: felix.
 * @createTime: 2017/12/11.
 */
@Service
public class ProcessService {

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	public DataTable<ProcessVO> getProcessByPage(DataTable dt) {
	    ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery().latestVersion();
		String category = (String) dt.getSearchParams().get("category");
        if (StringUtils.isNotBlank(category)){
            query.processDefinitionCategory(category);
        }
		dt.setTotal(((Long)query.count()).intValue());
		query.listPage((dt.getPageNumber() - 1) * dt.getPageSize(), dt.getPageSize())
				.stream()
                .forEach(processDefinition -> {
					Deployment deployment = repositoryService.createDeploymentQuery()
							.deploymentId(processDefinition.getDeploymentId()).singleResult();
					dt.getRows().add(ActivitiUtils.toProcessVO(processDefinition, deployment));
		});
        dt.setRows((List<ProcessVO>) dt.getRows()
                .stream()
                .sorted(Comparator.comparing(ProcessVO::getDeploymentTime).reversed())
                .collect(Collectors.toList()));
		return dt;
	}

	public InputStream resourceRead(String procDefId, String proInsId, String resType) throws Exception {

		if (StringUtils.isBlank(procDefId)){
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(proInsId).singleResult();
			procDefId = processInstance.getProcessDefinitionId();
		}
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();

		String resourceName = "";
		if (resType.equals("image")) {
			resourceName = processDefinition.getDiagramResourceName();
		} else if (resType.equals("xml")) {
			resourceName = processDefinition.getResourceName();
		}

		InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
		return resourceAsStream;
	}

	@Transactional(readOnly = false)
	public String updateStatus(String status, String procDefId) {
		if (status.equals("active")) {
			repositoryService.activateProcessDefinitionById(procDefId, true, null);
		} else if (status.equals("suspend")) {
			repositoryService.suspendProcessDefinitionById(procDefId, true, null);
		}
		return status;
	}
}
