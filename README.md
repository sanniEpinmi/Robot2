SQL DATABSE-----MSSQL
PASSWORD AND USERNAME IS IN application.propertiews.
KINDLY CHANGE THE CRDENTIAL WITH YOUR OWN.
http://localhost:5006/api/     BASE  URL


SAMPLE DATA  FOR CREATE
{
        "name": "Hsjon",
        "place": [
{"address": "no 2 white house","longitude":"12","latittude":"98"}],
        "resource": [
{"food":"salad",
"medication":"izal","ammunition":"water"}],
        "age": "10",
        "gender":"  M",
        "infectionstatus": "INFECTED",
        "infectionReport": 2

     

}


TO REPORT AN INFECTED,VALUE IS   1 FOR OBJECT NAME (infectionReport)
TO UPDATE infectionReport or report a victim ,id must be passed in the bodyrequest and path variable.
