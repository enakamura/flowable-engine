/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.flowable.cmmn.rest.service.api.management;

import org.flowable.cmmn.engine.CmmnEngine;
import org.flowable.cmmn.engine.CmmnEngines;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

/**
 * @author Tijs Rademakers
 */
@RestController
@Api(tags = { "Cmmn Engine" }, description = "Manage Cmmn Engine", authorizations = { @Authorization(value = "basicAuth") })
public class CmmnEngineResource {

    @ApiOperation(value = "Get engine info", tags = { "Cmmn Engine" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Indicates the engine info is returned."),
    })
    @GetMapping(value = "/cmmn-management/engine", produces = "application/json")
    public CmmnEngineInfoResponse getEngineInfo() {
        CmmnEngine cmmnEngine = CmmnEngines.getDefaultCmmnEngine();
        CmmnEngineInfoResponse response = new CmmnEngineInfoResponse();
        response.setName(cmmnEngine.getName());
        response.setVersion(CmmnEngine.VERSION);
        return response;
    }
}