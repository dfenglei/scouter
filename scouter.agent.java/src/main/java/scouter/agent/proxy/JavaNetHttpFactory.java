/*
 *  Copyright 2015 the original author or authors. 
 *  @https://github.com/scouter-project/scouter
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 */
package scouter.agent.proxy;
import scouter.agent.Logger;

public class JavaNetHttpFactory {

	private static final String HTTP_JAVA_CLIENT = "scouter.xtra.httpclient.JavaNetHttpClient";

	public static IHttpClient create(ClassLoader parent) {
		try {
			ClassLoader loader = LoaderManager.getHttpClient(parent);
			if (loader == null) {
				return HttpClient43Factory.dummy;
			}
			Class c = Class.forName(HTTP_JAVA_CLIENT, true, loader);
			return (IHttpClient) c.newInstance();
		} catch (Throwable e) {
			e.printStackTrace();
			Logger.println("A138", "fail to create", e);
			return HttpClient43Factory.dummy;
		}
	}
}
