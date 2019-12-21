<?php 
	require 'dbconnect.php';
	$sql = 'SELECT * FROM monan ORDER BY mamonan DESC LIMIT 6';// ORDER BY id DESC LIMIT 6
	$data = mysqli_query($conn,$sql);
 ?>
 <?php 
 	
 	///
 class mangmonan{
	function mangmonan($mamonan,$maloai,$tenmonan,$gia,$hinhanhmonan){
		$this->mamonan = $mamonan;
		$this->maloai = $maloai;
		$this->tenmonan = $tenmonan;
		$this->gia = $gia;
		$this->hinhanhmonan = $hinhanhmonan;
	}
}
$mangmonan = array();
while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangmonan, new mangmonan($row['mamonan'], $row['maloai'],$row['tenmonan'], $row['gia'], $row['hinhanhmonan']));
 	}
  
echo json_encode($mangmonan);
  ?>
