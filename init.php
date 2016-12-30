

<?php

$host = "fdb3.biz.nf";
$user = "2207229_flood";
$password = "Batman190";
$db = "2207229_flood";

$con = mysqli_connect($host,$user,$password,$db);
if(!$con)
{
	echo "Connection Error...".mysqli_connect_error();
}


?>

