<?php
    require_once __DIR__."/../../autoload/autoload.php";
    $open = "monan";
    $loaimonan = $db->fetchAll("loaimonan");

		            		foreach ($loaimonan as $item){
		            			echo $item['tenloai'] . $item['maloai']
		            		};
?>