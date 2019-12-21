<?php
	$open = "loaimonan";
    require_once __DIR__."/../../autoload/autoload.php";

    $loaimonan = $db->fetchAll("loaimonan");

    // $qr = "SELECT * FROM monan,quan_an,loaimonan WHERE monan.ma_qa = quan_an.ma_qa AND monan.maloai = loaimonan.maloai";
    // $monan = $db->fetchsql($qr);
    //

?>
<?php require_once __DIR__."/../../layouts/hearder.php"; ?>

    <div id="content-wrapper">

      <div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            Home
          </li>
          <li class="breadcrumb-item active"><a href="">Loại món</a></li>
        </ol>
      </div>
      <!-- /.container-fluid -->
      <div class="card mb-3">
          <div class="card-header"><a class="btn btn-success" href="add.php">Thêm mới loại</a></div>
<?php require_once __DIR__."/../../partials/notification.php"; ?>
          <div class="card-body">
			<table class="table table-bordered">
			    <thead>
			        <tr>
			            <th>Mã loại</th>
			            <th>Tên loại</th>
			        </tr>
			    </thead>
			    <tbody>
			    	<?php foreach ($loaimonan as $item): ?>
			        <tr>
			            <td><?php echo $item['maloai']?></td>
			            <td><?php echo $item['tenloai']?></td>
			            <td>
			            	<div class="btn-group-vertical">
			            		<a class="btn btn-xs btn-info" href="edit.php?maloai=<?php echo $item['maloai']?>"><i class="fa fa-edit"></i> Sửa</a>
								<a class="btn btn-xs btn-danger" href="delete.php?maloai=<?php echo $item['maloai']?>"><i class="fa fa-times"></i>Xóa</a>
			            	</div>
			            </td>
			        </tr>
			    	<?php endforeach ?>
			    </tbody>
			</table>
          </div>
        </div>

<?php require_once __DIR__. "/../../layouts/footer.php"; ?>