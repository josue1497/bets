INI_MEMORY=128m
MAX_MEMORY=512m
MAX_MEMORY_PERM=128m
TOMCAT_NAME=TOMCAT_NODO02
export JRE_HOME=/opt/hosting/software/java/jdk1.7.0_13


if [[ $EUID -ne 500 ]]; then
   echo "Los tomcat no deben ser levantado por un usuario distinto a ncamacho" 1>&2
   exit 1
fi
#---------------------------------#
# dynamically build the classpath #
#---------------------------------#
export THE_CLASSPATH=
for i in `ls /opt/hosting/software/tomcat/tomcat_nodo02/librerias_comunes/*.jar`
do
THE_CLASSPATH=${THE_CLASSPATH}:${i}
done

for i in `ls /opt/hosting/software/tomcat/tomcat_nodo02/lib/*.jar`
do
THE_CLASSPATH=${THE_CLASSPATH}:${i}
done


CLASSPATH=$CLASSPATH:$THE_CLASSPATH

export  CATALINA_OPTS="-DAmbiente=$TOMCAT_NAME -Xms$INI_MEMORY -Xmx$MAX_MEMORY -XX:MaxPermSize=$MAX_MEMORY_PERM -XX:OnOutOfMemoryError='/opt/hosting/admin/scripts/forceKillTomcat.sh $TOMCAT_NAME'"

