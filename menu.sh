#!/bin/bash
# Bash Menu Script Example
# http://askubuntu.com/questions/1705/how-can-i-create-a-select-menu-in-a-shell-script

PS3='Input your option: '
options=("deleteClasses" "compile" "codigo-test" "query-test" "unit-test" "suite-test" "updateSchema" "borar-db-HSQL" "datos-prueba-SQL" "sql-query" "sql-insert" "sql-update" "sql-alter" "createWar" "zip" "version" "Quit")

select opt in "${options[@]}"
do

case $opt in
        "compile")
            ant deleteClasses
            ant compile
            ;;
		"updateSchema")
			ant updateSchema
            ;;
		"createWar")
            ant createWar
            # Quitar los *.jars del war para copiar en menos tiempo
            # zip -d file.jar unwanted_file.txt
            zip -d ../../workspace.dist/Ingenio.dist/Ingenio.war WEB-INF/lib/\*
			;;
		"codigo-test")
			ant test -Dnombre_clase=CodigoTest
			;;
		"query-test")
			ant test -Dnombre_clase=QueryTest
			;;
		"unit-test")
			ant test -Dnombre_clase=Cto24HTest
			;;
		"suite-test")
			ant test -Dnombre_clase=SuiteTest
			;;
		"borar-db-HSQL")
			ant deleteDatabaseFolder
			;;
		"datos-prueba-SQL")
			ant test -Dnombre_clase=CrearEstructuraDBTest
            cd etc/importData
            groovy import.groovy
            cd ../..
            ant sql-insert
			;;
		"deleteClasses")
            rm -rf ~/.openxava
			ant deleteClasses
			;;
		"zip")
			ant zip
			;;
		"sql-query")
			ant sql-query
			;;
		"sql-update")
			ant sql-update
			;;
		"sql-alter")
			ant sql-alter
			;;
		"sql-insert")
			ant sql-insert
			;;
        "version")
			echo "OpenXava 6.3.2"
            ;;
		"Quit")
            break
            ;;
        *) echo invalid option;;
    esac
done

