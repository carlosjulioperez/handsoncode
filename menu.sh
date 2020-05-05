#!/bin/bash
# Bash Menu Script Example
# http://askubuntu.com/questions/1705/how-can-i-create-a-select-menu-in-a-shell-script

PS3='Input your option: '
options=("deleteClasses" "compile" "updateSchema" "test" "sql-query" "sql-alter" "sql-update" "deleteDatabaseFolder" "createWar" "zip" "version" "Quit")

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
		"test")
			ant test 
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
		"deleteDatabaseFolder")
			ant deleteDatabaseFolder
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

