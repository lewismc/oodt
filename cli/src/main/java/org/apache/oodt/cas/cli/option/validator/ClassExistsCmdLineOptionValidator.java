/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.oodt.cas.cli.option.validator;

//OODT imports
import org.apache.commons.lang.Validate;
import org.apache.oodt.cas.cli.option.CmdLineOptionInstance;
import org.apache.oodt.cas.cli.option.validator.CmdLineOptionValidator.Result.Grade;

/**
 * Validates arguments which are checked to see if they are valid classpaths.
 * 
 * @author bfoster (Brian Foster)
 */
public class ClassExistsCmdLineOptionValidator implements
      CmdLineOptionValidator {

   @Override
   public Result validate(CmdLineOptionInstance optionInst) {
      Validate.notNull(optionInst);

      for (String value : optionInst.getValues()) {
         try {
            Class.forName(value);
         } catch (Exception e) {
            return new Result(Grade.FAIL, "Value '" + value
                  + "' for option " + optionInst.getOption().getLongOption()
                  + " is not a valid class");
         }
      }
      return new Result(Grade.PASS, "Success");
   }
}
