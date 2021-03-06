/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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
package data.query;





import java.util.List;
/**
 * Custom extension to {@link Parameters}
 * 
 * @author Szymon Konicki
 *
 */
public class JpaParameters extends Parameters<JpaParameters, JpaParameter> {

	public JpaParameters(List<JpaParameter> parameters, int sortIndex, int pageableIndex) {
		super(parameters,sortIndex, pageableIndex);
	}

	private JpaParameters(List<JpaParameter> parameters) {
		super(parameters);
	}

	@Override
	protected JpaParameters createFrom(List<JpaParameter> parameters) {
		return new JpaParameters(parameters);
	}

	
}
