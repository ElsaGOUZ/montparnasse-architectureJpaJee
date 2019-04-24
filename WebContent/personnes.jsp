<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/bootstrap.css" />
</head>
<body>
	<form action="ServletPersonne">
		<section class="container">
			<h1>FORMULAIRE D'INSCRIPTION</h1>
			<div class="form-group">
				<input type="hidden" name="id" value="${id}" />
				<!-- input hidden utilisé ici permet d'ajouter un id caché  afin d'ajouter ou modifier si id=0-->
				<div>
					<input type="text" class="form-control"
						placeholder="Saisir votre Nom" type="text" name="nom"
						value="${nom}" required> <br> 
						<input type="text"
						class="form-control" placeholder="Saisir votre Prenom" type="text"
						name="prenom" value="${prenom}" required> <br>
						 <input type="number" class="form-control" placeholder="Saisir votre Age"
						type="number" name="age" value="${age}" required> <br>
						 <input type="text" class="form-control" placeholder="Saisir votre login"
						type="text" name="login" value="${login}" required> <br>
						 <input type="password" class="form-control" placeholder="Saisir votre pass"
						type="password" name="mdp" value="${mdp}" required> <br>
				</div>
				<!--liste deroulante des adresses -->
		Adresse:<br />
		<select name="idadresse">
			<!--ON DONNE le nom de idadresse au select pour l'appeler dans la servlet -->
			<optgroup>
			<option value="0">----</option>
			<c:if test="${!empty adresses}">
			<c:forEach items="${adresses}" var="a">
					<option value="${a.idAdresse}"><c:out
							value="${a.nomRue} ${a.ville} ${a.numRue} ${a.cp}">
							</c:out></option>
			</c:forEach>
			</c:if> 
			</optgroup>
		</select>
			</div>
			<div class="form-group form-check">
				<button type="submit" name="ajouter" value="ajouter"
					class="btn btn-primary">Ajouter</button>
			</div>
			<div class="form-group form-check">
				<button type="submit" name="modifier" value="modifier"
					class="btn btn-primary">Modifier</button>
			</div>
		</section>
	</form>
	<div class="container">
		<h2>Liste des personne en Base de donnees</h2>
		<table class="table table-striped table-dark">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">NOM</th>
					<th scope="col">PRENOM</th>
					<th scope="col">AGE</th>
					<th scope="col">LOGIN</th>
					<th scope="col">MDP</th>
					<th scope="col">SUPPRIMER</th>
					<th scope="col">MODIFIER</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty personnes}">
					<c:forEach items="${personnes}" var="x">
						<tr>
							<td><c:out value="${x.id}" /></td>
							<td><c:out value="${x.nom}" /></td>
							<td><c:out value="${x.prenom}" /></td>
							<td><c:out value="${x.age}" /></td>
							<td><c:out value="${x.connexion.login}" /></td>
							<td><c:out value="${x.connexion.mdp}" /></td>
							<td><a href="SupprimerPersonne?id=${x.id}">supprimer</a></td>
							<td><a href="ModifierPersonne?id=${x.id}">modifier</a></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</body>
</html>