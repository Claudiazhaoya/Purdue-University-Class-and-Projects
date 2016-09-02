<?PHP
session_start();
?>

<html>
<head>
<title>Movies</title>
</head>
</body>
<br>
<?PHP
if($_SESSION['log']!=0){
	echo "Hi ",$_SESSION['firstname'],"  ",$_SESSION['lastname'],"  ";
	echo ('<a href="Logout.php">Logout</a>');
}
?>
<br>
<form name="movie" method="POST" action="MovieInfo.php">
<br>
Movie Title:<br><input type="text" name="movietitle" value="Title"><br>
<input type="submit" name='Movie'value="Submit"><br>
<a href="Actors.php">Actor</a><br>
<a href="Login.php">Login/Register</a>
</body>
</html>
 
