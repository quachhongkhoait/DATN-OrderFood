<?php 
	require 'dbconnect.php';
	$sql = 'SELECT * FROM quan_an';// ORDER BY id DESC LIMIT 6
	$data = mysqli_query($conn,$sql);
 ?>
 <?php 
 	
 	///
 class mangquanan{
	function mangquanan($ma_qa,$ten_qa,$hinhanh_qa,$diachi_qa,$thoigianphucvu){
		$this->ma_qa = $ma_qa;
		$this->ten_qa = $ten_qa;
		$this->hinhanh_qa = $hinhanh_qa;
		$this->diachi_qa = $diachi_qa;
		$this->thoigianphucvu = $thoigianphucvu;
	}
}
$mangquanan = array();
while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangquanan, new mangquanan($row['ma_qa'], $row['ten_qa'],$row['hinhanh_qa'], $row['diachi_qa'], $row['thoigianphucvu']));
 	}
  
echo json_encode($mangquanan);
  ?>