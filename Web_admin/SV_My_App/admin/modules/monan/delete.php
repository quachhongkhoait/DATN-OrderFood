<?php
	$open = "monan";
    require_once __DIR__."/../../autoload/autoload.php";

    $mamonan = intval(getInput("mamonan"));

    $delete_monan = $db->fetchID("monan","mamonan",$mamonan);
    if (empty($delete_monan)) {
    	$_SESSION['error'] =  "Dữ liệu không tồn tại!";
    	redirectAdmin("monan");
    }
    $num = $db->delete("monan","mamonan",$mamonan);
    if ($num>0) {
    	$_SESSION['success'] =  "Xóa thành công!";
    	redirectAdmin("monan");
    } else {
    	$_SESSION['error'] =  "Xóa thất bại!";
    	redirectAdmin("monan");
    }
?>