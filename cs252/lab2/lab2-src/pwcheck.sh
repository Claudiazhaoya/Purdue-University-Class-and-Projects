#!/bin/bash
Password=$(cat $1)  #Sets the vairable Password to the string in the file
Length=${#Password} #finds the length of the Password
COUNT=0			#sests count to zero
if [ $Length -ge 6 ]; then		
	if [ $Length -le 32 ]; then	#if the length is between 6 and 32 g
		let COUNT=COUNT+Length	#set count to the length of the psswd

		if egrep -q [0-9] $1; then # if has a number add five
			let COUNT=COUNT+5
		fi

		if egrep -q "[#$\+%@]" $1; then   #if special char add five
			let COUNT=COUNT+5
		fi

		if egrep -q [a-zA-Z] $1; then   #if alpha char add five
			let COUNT=COUNT+5
		fi
		
		if egrep -q "([a-z][a-z][a-z])" $1; then # if three lower in a row subtract three
			let COUNT=COUNT-3
		fi

		if egrep -q "([A-Z][A-Z][A-Z])" $1; then #if three upper in a row subtract three
			let COUNT=COUNT-3
		fi

		if egrep -q "([0-9][0-9][0-9])" $1; then #if three numbers in a row subtract three
			let COUNT=COUNT-3
		fi

		if egrep -q "([a-zA-Z0-9])\1+" $1; then #if two or more of the same alpha numberic subtract 10
			let COUNT=COUNT-10
		fi
		echo "Password Score: " $COUNT   #print out score




	else
		echo "Error: Password length invalid"  #if bad length print
	fi

else
	echo "Error: Password length invalid"    #if bad length print
fi
