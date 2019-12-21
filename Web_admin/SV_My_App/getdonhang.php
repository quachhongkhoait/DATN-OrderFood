<?php 
	require 'dbconnect.php';
	// $page = $_GET['page'];
	$makh =$_POST['makh'];
	// $maqa =1;
	// $space =5;
	// $limit = ($page -1) *$space;
	$mangdonhang = array();
	$sql = 'SELECT * FROM don_dathang WHERE status =0 and ma_kh = '.$makh.'';
	// $sql = 'SELECT * FROM monan WHERE ma_qa = '.$maqa.' LIMIT '.$limit.','.$space.'';
	$data = mysqli_query($conn,$sql);
 ?>
 <?php 
 class mangdonhang{
	function mangdonhang($ma_dondh,$tong_gia,$dia_chi){
		$this->ma_dondh = $ma_dondh;
		$this->tong_gia = $tong_gia;
		$this->dia_chi = $dia_chi;
	}
}

while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangdonhang, new mangdonhang($row['ma_dondh'], $row['tong_gia'],$row['dia_chi']));
 	}
  
echo json_encode($mangdonhang);
  ?>