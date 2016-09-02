<?PHP
error_reporting(E_ALL);
ini_set('display_errors',1);
session_start();
$_SESSION['invalid']=0;
echo "IN PROCESSING";

if(isset($_POST['Login'])){
	echo "IN LOGIN";
	$username = $_POST['username'];
	$password = $_POST['password'];
    try {
	   // open connection to MongoDB server
        $conn = new MongoClient();
        // access database
        $db = $conn->test;
        // access collection
        $collection = $db->items;
        // formulate AND query
        $criteria = array('username'=>$_POST['username'],'password'=>$_POST['password']);
        // retrieve only 'name' and 'price' keys
        $fields = array('firstName','lastName');
        // execute query
        $cursor = $collection->find($criteria, $fields);
        // iterate through the result set
        // print each documenta
	echo $cursor->count();
	if($cursor->count()==0){
	$_SESSION['invalid']=1;
	}
        foreach ($cursor as $obj) {
            $_SESSION['firstname']=$obj['firstName'];
	    $_SESSION['lastname']=$obj['lastName'];
	    $_SESSION['invalid']=0;
        }
        // disconnect from server
        $conn->close();
    } catch (MongoConnectionException $e) {
        die('Error connecting to MongoDB server');
    } catch (MongoException $e) {
        die('Error: ' . $e->getMessage());
    }
	if($_SESSION['invalid']==1){
	header('Location: Error.php');
	}else{
	$_SESSION['log']=1;
	header('Location: MovieDB.php');
	}
	
}else if(isset($_POST['Register'])){
	$username = $_POST['username'];
	$password = $_POST['password'];
	$firstname = $_POST['firstname'];
	$lastname = $_POST['lastname'];
	$_SESSION['username']=$_POST['username'];
	$_SESSION['password']=$_POST['password'];
	$_SESSION['firstname']=$_POST['firstname'];
	$_SESSION['lastname']=$_POST['lastname'];
	$_SESSION['log']=1;
    try {
        // open connection to MongoDB server
        $conn = new MongoClient();
        // access database
        $db = $conn->test;
        // access collection
        $collection = $db->items;
        $item = array('username'=>$_SESSION['username'],'password'=>$_SESSION['password'],'firstName'=>$_SESSION['firstname'],'lastName'=>$_SESSION['lastname']);
	$collection->insert($item);
        $conn->close();
    } catch (MongoConnectionException $e) {
        die('Error connecting to MongoDB server');
    } catch (MongoException $e) {
        die('Error: ' . $e->getMessage());
    }
	header('Location: MovieDB.php');
}else if(isset($_POST['Movie'])){
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
        echo $cursor->count();
	echo "<br>";
	if($cursor->count()==0){
	header('Location: Movie.php');
	}
        foreach ($cursor as $obj) {
            echo $obj['genre'];
	    echo "<br>";
	    echo $obj['releaseYear'];
	    echo "<br>";
	    echo $obj['plotSummary'];
            echo "<br>";
        }
	$criteria = array('movie'=>$_POST['movietitle']);
	$fields = array('castMember');
	$cursor = $collection->find($criteria,$fields);
	echo $cursor->count();
	echo "<br>";
	foreach ($cursor as $obj){
		echo $obj['castMember'];
		echo "<br>";
	}
        // disconnect from server
        $conn->close();
    } catch (MongoConnectionException $e) {
        die('Error connecting to MongoDB server');
    } catch (MongoException $e) {
        die('Error: ' . $e->getMessage());
    }
}
?>

