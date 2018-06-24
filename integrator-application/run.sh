#!/usr/bin/ksh

trap 'RC=$? ; _imprime ERRO "Falha na execucao RC=$RC" ;_termino $RC ; exit $RC' ERR TERM QUIT HUP

. /amb/SOURCE/script/config_env.sh

JOB_NAME=$1
DT_START_FILTER=$2
DT_END_FILTER=$3

export BASE_PATH=${DIR_BIN}/workflow-integrator

export CLASSPATH=$BASE_PATH/lib/workflow-integrator-application-1.0.0.jar:$BASE_PATH/lib/com.ibm.jbatch-tck-spi-1.0.jar:$BASE_PATH/lib/commons-logging-1.2.jar:$BASE_PATH/lib/hsqldb-2.3.4.jar:$BASE_PATH/lib/jackson-annotations-2.9.0.jar:$BASE_PATH/lib/jackson-core-2.9.0.jar:$BASE_PATH/lib/jackson-databind-2.9.0.jar:$BASE_PATH/lib/jasypt-1.9.2.jar:$BASE_PATH/lib/jasypt-spring31-1.9.2.jar:$BASE_PATH/lib/javax.batch-api-1.0.jar:$BASE_PATH/lib/jcl-over-slf4j-1.7.21.jar:$BASE_PATH/lib/jettison-1.2.jar:$BASE_PATH/lib/log4j-1.2.17.jar:$BASE_PATH/lib/ojdbc6-11.2.0.4.jar:$BASE_PATH/lib/slf4j-api-1.7.21.jar:$BASE_PATH/lib/slf4j-log4j12-1.7.21.jar:$BASE_PATH/lib/spring-aop-4.3.0.RELEASE.jar:$BASE_PATH/lib/spring-batch-core-3.0.7.RELEASE.jar:$BASE_PATH/lib/spring-batch-infrastructure-3.0.7.RELEASE.jar:$BASE_PATH/lib/spring-beans-4.3.0.RELEASE.jar:$BASE_PATH/lib/spring-context-4.3.0.RELEASE.jar:$BASE_PATH/lib/spring-core-4.3.0.RELEASE.jar:$BASE_PATH/lib/spring-expression-4.3.0.RELEASE.jar:$BASE_PATH/lib/spring-jdbc-4.3.0.RELEASE.jar:$BASE_PATH/lib/spring-retry-1.1.0.RELEASE.jar:$BASE_PATH/lib/spring-tx-4.3.0.RELEASE.jar:$BASE_PATH/lib/spring-web-4.3.0.RELEASE.jar:$BASE_PATH/lib/xmlpull-1.1.3.1.jar:$BASE_PATH/lib/xpp3_min-1.1.4c.jar:$BASE_PATH/lib/xstream-1.4.7.jar:$BASE_PATH/config/database.properties:$BASE_PATH/config/passwords.properties:$BASE_PATH/config/workflowintegrator.properties:$BASE_PATH/config/config.xml:$BASE_PATH/config/beans-workflow-integrator.xml:$BASE_PATH/config/log4j.xml:$BASE_PATH/.:$BASE_PATH/config:$BASE_PATH/config/jcl-over-slf4j-1.7.21.jar:$BASE_PATH/config/slf4j-api-1.7.21.jar:$BASE_PATH/config/slf4j-log4j12-1.7.21.jar

#export FRASE_CRIPT="workflowintegrator"
export ENV_SPRING_BATCH_PASS=workflowintegrator

_inicio

cd $BASE_PATH

_imprime "BASE_PATH:" "${BASE_PATH}"
_imprime "CLASSPATH:" "${CLASSPATH}"

java -Dlog4j.configuration=config/log4j.xml -cp $CLASSPATH br.com.nextel.workflowintegrator.main.ControlMStarter jobName=$JOB_NAME dtStartFilter =$DT_START_FILTER dtEndFilter=$DT_END_FILTER


_termino

