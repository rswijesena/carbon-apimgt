/**
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package org.wso2.carbon.apimgt.usage.publisher;

import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.impl.dao.ApiMgtDAO;
import org.wso2.carbon.apimgt.usage.publisher.dto.AlertTypeDTO;

import java.sql.SQLException;

public class AlertTypesPublisher extends APIMgtCommonExecutionPublisher {

    public AlertTypesPublisher() {
        super();
    }

    public void saveAndPublishAlertTypesEvent(String checkedAlertList, String emailList, String userName, String agent
            ) throws APIManagementException {

        try {
            if (publisher == null) {
                this.initializeDataPublisher();
            }

            if (!enabled || skipEventReceiverConnection) {
                throw new APIManagementException("Data publisher is not enabled");
            }

            ApiMgtDAO apiMgtDAO = ApiMgtDAO.getInstance();
            apiMgtDAO.addAlertTypesConfigInfo(userName, emailList, checkedAlertList, agent);

            AlertTypeDTO alertTypeDTO = new AlertTypeDTO();
            alertTypeDTO.setAlertTypes(checkedAlertList);
            alertTypeDTO.setEmails(emailList);
            alertTypeDTO.setUserName(userName);
            if ("p" == agent) {
                alertTypeDTO.setPublisher(true);
                alertTypeDTO.setSubscriber(false);
            } else if ("s" == agent) {
                alertTypeDTO.setSubscriber(true);
                alertTypeDTO.setPublisher(false);
            }else if("a" == agent){
                alertTypeDTO.setSubscriber(true);
                alertTypeDTO.setPublisher(true);
            }
            publisher.publishEvent(alertTypeDTO);

        } catch (SQLException e) {
            handleException("Error while saving alert types", e);
        }

    }

    private void handleException(String msg, Throwable t) throws APIManagementException {
        log.error(msg, t);
        throw new APIManagementException(msg, t);
    }

}
