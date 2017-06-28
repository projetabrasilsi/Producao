<?php
	$nome 		= $_POST["name"];
	$email 		= $_POST["email"];
	$phone	 	= $_POST["phone"];
	$message 	= $_POST["message"];

	$mail_message .= "Nome: ".$nome."\n";
	$mail_message .= "Telefone: ".$phone."\n\n";
	$mail_message .= $message;

	// $location 	= $_POST["location"];

	mail('projetabrasilsi@gmail.com', 'Contato - AcheiPet', $mail_message, 'From: ' . $email);

	// echo '<META HTTP-EQUIV="Refresh" Content="0; URL='. $location .'">';
?>