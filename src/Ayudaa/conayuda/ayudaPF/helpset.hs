<?xml version='1.0' encoding='ISO-8859-1' ?>
<!DOCTYPE helpset
 PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 2.0//EN"
 "http://java.sun.com/products/javahelp/helpset_2_0.dtd">
<helpset version="2.0">
 <!-- title -->
 <title>Java Development Environment - Help</title>

 <!-- maps -->
 <maps>
 <homeID>inicio</homeID>
 <mapref location="mapa.jhm" />
 </maps>

 <!-- views -->
 <view xml:lang="es" mergetype="javax.help.UniteAppendMerge">
 <name>TOC</name>
 <label>Table Of Contents</label>
 <type>javax.help.TOCView</type>
 <data>toc.xml</data>
 </view>

 <view xml:lang="es" mergetype="javax.help.SortMerge">
 <name>Index</name>
 <label>Index</label>
 <type>javax.help.IndexView</type>
 <data>indice.xml</data>
 </view>

 <view>
      <name>Buscar</name>
      <label>Buscar</label>
      <type>javax.help.SearchView</type>
      <data engine="com.sun.java.help.search.DefaultSearchEngine">
         JavaHelpSearch
      </data>
   </view>
</helpset>
