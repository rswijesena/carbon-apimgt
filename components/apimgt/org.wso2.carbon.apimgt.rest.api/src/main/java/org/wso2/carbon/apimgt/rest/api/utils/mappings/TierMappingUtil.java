/*
 *
 *  Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */

package org.wso2.carbon.apimgt.rest.api.utils.mappings;

import org.wso2.carbon.apimgt.api.model.Tier;
import org.wso2.carbon.apimgt.rest.api.dto.TierDTO;

/**
 * This class is used as a util class to map attributes of backend Tier model with Tier DTO in REST API
 */
public class TierMappingUtil {
    /**
     * Map backend tier model to DTO
     *
     * @param tier Backend tier model
     * @return TierDTO Corresponding DTO which maps with the input backend attributes
     */
    public static TierDTO fromTiertoDTO(Tier tier){
        TierDTO dto = new TierDTO();
        dto.setName(tier.getName());
        dto.setDescription(tier.getDescription());
        //do we need to add requestspermin attr?
        //dto.setAttributes(tier.getTierAttributes()); backend returns <String,object> by APIDescriptionGenUtil:no use
        return dto;
    }

    /**
     * Map DTO to backend tier model
     *
     * @param dto Tier DTO
     * @return Tier Corresponding backend model which maps with DTO attributes
     */
    public static Tier fromDTOtoTier(TierDTO dto){
        Tier tier = new Tier(dto.getName());
        tier.setDescription(dto.getDescription());
        //tier.setTierAttributes(dto.getAttributes());
        return tier;
    }
}
