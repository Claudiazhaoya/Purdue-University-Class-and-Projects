#!/bin/bash
Date=$(date +"%Y-%m-%d-%T")  #set variables to values needed
name=$1
dir=$2
interval=$3
maxBack=$4
COUNT=0
Change=""
Check=1



if [ -d $dir ]; then
	echo 	#checks if backup directry exists if it does not it makes it
else
	mkdir -p $dir
fi

echo $(cat $1) > $dir/"$Date-$name"   #backsup file right away

	while true
	Date=$(date +"%Y-%m-%d-%T")		#start infinate loop and set date variable

	do
		sleep $interval				#sleep for specified time
			for file in $dir/* ; do
			let COUNT=COUNT+1		#gets the newest backup in the directory
			OLD=${file##*/}
			done
		Change=$(diff $dir/$OLD $1) #diffs the file and the backup

		if	[ "$Change" != "" ]		#if change then
			then
				Number="$(ls $dir | wc -l)" # check if max backup

				if [ $Number -ge $maxBack ]; then	#if max then delete
					rm $dir/$(ls -tr1 $dir | head -n 1) 
				fi

				echo  $(cat $1) > $dir/$Date-$name  #backs up file
				
				
				echo $Change > tmp-message		#sends email
				/usr/bin/mailx -s "file has been changed" $USER < tmp-message
			else
			echo
		fi
		Change=""		#sets the diff output to nothing
	done #done
