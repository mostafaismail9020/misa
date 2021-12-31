
#if($contextDescription && $contextDescription != '')
			<!-- $contextDescription -->
#end
			<Context path="$contextPath" docBase="$contextDocBase" $!contextAdditionalAttributes>
				<Manager pathname="" />
				
#if($contextPath == '/investsaudistorefront' || $contextPath == '/sagiastorefront')
                <Manager className="org.apache.catalina.ha.session.DeltaManager" expireSessionsOnShutdown="false" notifyListenersOnReplication="true" />

#else
                 <Manager pathname="" />
#end

#if($contextLoader && $contextLoader != '')
				$contextLoader
#end
#if($contextAdditionalElements && $contextAdditionalElements != '')
				$contextAdditionalElements
#end
			</Context>