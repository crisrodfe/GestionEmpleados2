<?xml version="1.0" encoding="UTF-8"?>


<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
   <xsl:template match='/'>
    <html><xsl:apply-templates /></html>
   </xsl:template>

   <xsl:template match='Empleados'>
         <head>
             <title>LISTADO DE EMPLEADOS</title>
         </head>
         <body>
             <h1>LISTA DE EMPLEADOS</h1>
             <table border='1'>
             <tr><th>ID</th><th>Nombre</th><th>Apellido1</th><th>Apellido2</th><th>Salario</th></tr>
             <xsl:apply-templates select='empleado' />
             </table>
         </body>
    </xsl:template>
	<xsl:template match='empleado'>
	 <tr>
		 <xsl:apply-templates />
	 </tr>
	</xsl:template>
	<xsl:template match='id|nombre|apell1|apell2|salario'>
	 <td>
		 <xsl:apply-templates />
	 </td>
	</xsl:template>
</xsl:stylesheet>
