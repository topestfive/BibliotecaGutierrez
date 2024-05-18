<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <title>Gestionar libros</title>
    </head>
    <body>
        <div class="container-fluid  mt-4" style="text-align: center;">
            <h1>Gestionar libros</h1>  
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-12">
                    <div class="card mt-3">
                        <div class="card-body">
                            <form action="LibroCTO?menu=Libros" method="POST">
                                <div class="form-group mb-3">
                                    <label for="txtIsbn" class="form-label">Isbn</label>
                                    <input type="text" value="${libro.getIsbn()}" id="txtIsbn" name="txtIsbn" class="form-control" required>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="txtNombres" class="form-label">Nombre</label>
                                    <input  type="text" value="${libro.getNombre()}" id="txtNombres" name="txtNombres" class="form-control" required>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="txtAutor" class="form-label">Autor</label>
                                    <input  type="text" value="${libro.getAutor()}" id="txtAutor" name="txtAutor" class="form-control" required>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="txtEditorial" class="form-label">Editorial</label>
                                    <input  type="text" value="${libro.getEditorial()}" id="txtEditorial" name="txtEditorial" class="form-control" required>
                                </div>
                                <div class="form-group mb-3">
                                    <label for="txtAnio" class="form-label">Año</label>
                                    <input  type="text" value="${libro.getAnio()}" id="txtAnio" name="txtAnio" class="form-control" required>
                                </div>
                                <div class="d-flex justify-content-center">
                                    <input type="submit" name="accion" value="Agregar" class="btn btn-primary me-2 w-50">
                                    <input type="submit" name="accion" value="Actualizar" class="btn btn-primary w-50">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-md-12">
                    <div class="table-responsive mt-5">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>ISBN</th>
                                    <th>NOMBRE</th>
                                    <th>AUTOR</th>
                                    <th>EDITORIAL</th>
                                    <th>AÑO</th>
                                    <th>ACCIONES</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="list" items="${lista}">
                                    <tr>
                                        <td>${list.getIsbn()}</td>
                                        <td>${list.getNombre()}</td>
                                        <td>${list.getAutor()}</td>
                                        <td>${list.getEditorial()}</td>
                                        <td>${list.getAnio()}</td>
                                        <td>
                                            <a class="btn btn-warning mb-2" href="LibroCTO?menu=Libros&accion=Editar&isbn=${list.getIsbn()}">Editar</a>
                                            <a class="btn btn-danger" href="LibroCTO?menu=Libros&accion=Eliminar&isbn=${list.getIsbn()}">Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
