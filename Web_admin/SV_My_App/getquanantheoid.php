<?php 
	require 'dbconnect.php';
	// $page = $_GET['page'];
	$maqa =$_POST['maqa'];
	// $maqa =1;
	// $space =5;
	// $limit = ($page -1) *$space;
	$mangmonan = array();
	$sql = 'SELECT * FROM monan WHERE ma_qa = '.$maqa.'';
	// $sql = 'SELECT * FROM monan WHERE ma_qa = '.$maqa.' LIMIT '.$limit.','.$space.'';
	$data = mysqli_query($conn,$sql);
 ?>
 <?php 
 class mangmonan{
	function mangmonan($mamonan,$maloai,$tenmonan,$gia,$hinhanhmonan){
		$this->mamonan = $mamonan;
		$this->maloai = $maloai;
		$this->tenmonan = $tenmonan;
		$this->gia = $gia;
		$this->hinhanhmonan = $hinhanhmonan;
	}
}

while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangmonan, new mangmonan($row['mamonan'], $row['maloai'],$row['tenmonan'], $row['gia'], $row['hinhanhmonan']));
 	}
  
echo json_encode($mangmonan);
  ?>