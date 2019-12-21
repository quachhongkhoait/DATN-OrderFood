<?php
	require_once __DIR__. "/database.php";
	$db = new database;
	require 'dbconnect.php';
	$tongtien = $_POST['tongtien'];
	$ghichu = $_POST['ghichu'];
	$arr = $_POST['arraykh'];
	$arrma = $_POST['arrayma'];
	$new_arrkh = json_decode($arr,true);
	$new_arrma = json_decode($arrma,true);
	// print_r($new_arrkh);
	// print_r($new_arrma);
	// print_r($tongtien);
    	$error =[];
    foreach ($new_arrkh as $value) {
    	$ma_kh =$value['makh'];
    	$address = $value['address'];
		}

	$data = [
    		"ma_kh" => $ma_kh,
    		"tong_gia" => $tongtien,
    		"dia_chi" => $address,
    		"ghi_chu" => $ghichu
    		];
	$id_ddh = $db->insert("don_dathang",$data);
	if ($id_ddh >0) {
		foreach ($new_arrma as $key => $value) {
    		$data2 = [
    			'ma_dondh'	    => $id_ddh,
    			'mamonan' 		=> $value['mama'],
    			'soluong' 	    => $value['soluong']
    		];
    		$id_ctdh = $db->insert("chitiet_dh",$data2);
		}
		echo $response["success"] = 1;
	}
?>