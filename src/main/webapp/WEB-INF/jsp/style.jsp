<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/stylesheets/container.css"
	var="containerCss" />
<spring:url value="/resources/stylesheets/base.css" var="baseCss" />
<spring:url value="/resources/javascript/accordion-menu.js" var="menuJs" />
<spring:url value="/resources/javascript/general.js" var="generalJs" />
<link rel="stylesheet" type="text/css" href="${containerCss}" />
<link rel="stylesheet" type="text/css" href="${baseCss}" />
<script type="text/javascript" src="${menuJs}" ></script>
<script type="text/javascript" src="${generalJs}" ></script>