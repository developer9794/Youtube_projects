<?php

$con = @mysqli_connect('localhost', 'root', '', 'vclub');
if (!$con) {
    echo "Error: " . mysqli_connect_error();
	exit();
}
function gameiwd($con)
{
$selectruser=mysqli_query($con,"select `gameid` from `tbl_gameid` order by id desc limit 1");
$userresult=mysqli_fetch_array($selectruser);
echo">>>". $userresult["gameid"];
	}

?>