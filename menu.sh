#!/bin/bash
# Bash Menu Script Example
# http://askubuntu.com/questions/1705/how-can-i-create-a-select-menu-in-a-shell-script

PS3='Input your option: '
options=("deleteClasses" "compile" "updateSchema" "suite-test" "unit-test" "borar-db-HSQL" "datos-prueba-HSQL" "sql-query" "sql-insert" "sql-alter" "sql-update"  "createWar" "zip" "version" "Quit")

select opt in "${options[@]}"
do

case $opt in
        "compile")
            ant compile
            ;;
		"updateSchema")
			ant updateSchema
            ;;
		"createWar")
            ant createWar
			;;
		"suite-test")
			ant test -Dnombre_clase=SuiteTest
			;;
		"unit-test")
			ant test -Dnombre_clase=CanaTest
			;;
		"borar-db-HSQL")
			ant deleteDatabaseFolder
			;;
		"datos-prueba-HSQL")
			ant test -Dnombre_clase=CrearEstructuraDBTest
            cd etc/importData
            groovy import.groovy
            cd ../..
            ant sql-insert
			;;
		"deleteClasses")
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
			echo "OpenXava 6.2.2"
            ;;
		"Quit")
            break
            ;;
        *) echo invalid option;;
    esac
done

