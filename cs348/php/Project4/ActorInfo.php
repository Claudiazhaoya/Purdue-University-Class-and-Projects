<?PHP
error_reporting(E_ALL);
ini_set('display_errors',1);
session_start();
?>
<html>
<head>
<title>Actor Info</title>
</head>
<br>
<?PHP
if($_SESSION['log']!=0){
echo "Hi ",$_SESSION['firstname'],"  ",$_SESSION['lastname'],"  ";
echo ('<a href="Logout.php">Logout</a>');
}
if(isset($_POST['Actor'])){
        $title = $_POST['actors'];
            try {
        // open connection to MongoDB server
        $conn = new MongoClient();
        // access database
        $db = $conn->test;
        // access collection
        $collection = $db->items;
        // formulate AND query
        $criteria = array('actorName'=>$_POST['actors']);
        // retrieve only 'name' and 'price' keys
        $fields = array('birthdate');
        // execute query
        $cursor = $collection->find($criteria, $fields);
        if($cursor->count()==0){
        header('Location: Actors.php');
        }
        foreach ($cursor as $obj) {
        }
	echo "<br>";
	echo "<b>Actor Name: </b><br>";
	echo $_POST['actors'];
	echo "<br><br>";
	echo "<b> Birth Date: </b>";
	echo "<br>";
	echo $obj['birthdate'];
	echo "<br><br>";
        $criteria = array('castMember'=>$_POST['actors']);
        $fields = array('movie');
        $cursor = $collection->find($criteria,$fields);
	echo "<b> Movies: </b>";
	echo "<br>";
        foreach ($cursor as $obj){
	echo $obj['movie'];
        }
	echo "<br><br>";
        $criteria = array('actor'=>$_POST['actors']);
        $fields = array('fileNumber','fileName');
        $cursor = $collection->find($criteria,$fields);
        foreach ($cursor as $obj){
	echo "<br>";
	echo"<img src=/images/",$obj['fileName'],">";
        }
	echo"<br>";
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


