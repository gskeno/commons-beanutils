/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.apache.commons.beanutils;


import java.util.ArrayList;
import java.util.List;

public class Paper {
   private List<String> signers;

   private String[] tutors;

    public void setSigners(List<String> signers) {
        this.signers = signers;
    }

    public String[] getTutors() {
        return tutors;
    }

    public void setTutors(String[] tutors) {
        this.tutors = tutors;
    }

    public List<String> getSigners() {
        return signers;
    }

    public void addSinger(String signer){
       if (signers == null){
           signers = new ArrayList<>();
       }
       signers.add(signer);
   }

    @Override
    public String toString() {
        return "Paper{" +
                "signers=" + signers +
                '}';
    }
}
