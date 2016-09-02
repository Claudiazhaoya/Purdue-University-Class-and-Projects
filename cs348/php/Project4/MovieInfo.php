<?PHP
error_reporting(E_ALL);
ini_set('display_errors',1);
session_start();
?>
<html>
<head>
<title>Movie Information</title>
</head>
<br>
<?PHP
if($_SESSION['log']!=0){
	echo "Hi ",$_SESSION['firstname'],"  ",$_SESSION['lastname'],"  ";
	echo ('<a href="Logout.php">Logout</a>');
}
if(isset($_POST['Movie'])){
        $title = $_POST['movietitle'];
            try {
        // open connection to MongoDB server
        $conn = new MongoClient();
        // access database
        $db = $conn->test;
        // access collection
        $collection = $db->items;
        // formulate AND query
        $criteria = array('title'=>$_POST['movietitle']);
        // retrieve only 'name' and 'price' keys
        $fields = array('genre','releaseYear','plotSummary');
        // execute query
        $cursor = $collection->find($criteria, $fields);
        echo "<br>";
        if($cursor->count()==0){
        header('Location: Movie.php');
        }
        foreach ($cursor as $obj) {
        }
	echo "<b>Genre: </b>";
	echo "<br>";
	echo $obj['genre'];
	echo "<br><br>";
	echo "<b>Release Year: </b>";
	echo "<br>";
	echo $obj['releaseYear'];
	echo "<br><br>";
	echo "<b>Plot Summary: </b>";
	echo "<br>";
	echo $obj['plotSummary'];
        $criteria = array('movie'=>$_POST['movietitle']);
        $fields = array('castMember');
        $cursor = $collection->find($criteria,$fields);
        echo $cursor->count();
        echo "<br><br>";
	echo "<b>Cast Members: </b>";
	echo "<br>";
        foreach ($cursor as $obj){
                echo $obj['castMember'];
                echo "<br>";
        }
		echo "<br>";
	$criteria = array('movieTitle'=>$_POST['movietitle']);
	$fields = array('rating');
	$cursor = $collection->find($criteria,$fields);
	$average = 0;
	foreach ($cursor as $obj){
		$average = $average+$obj['rating'];
	}
		$average = $average/$cursor->count();
		echo "<b> Average rating: </b>";
		echo "<br>";
		echo $average;
		echo "<br>";

        // disconnect from server
        $conn->close();
    } catch (MongoConnectionException $e) {
        die('Error connecting to MongoDB server');
    } catch (MongoException $e) {
        die('Error: ' . $e->getMessage());
    }
}

?>
<a href="Movie.php">Movie</a>
<br>
<a href="Actors.php">Actor</a>
<br>
<a href="Login.php">Login/Register</a>
<br>
</body>
</html>

