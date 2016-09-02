.global quicksort
partition:                      @here is where I set the I and J counter and
				@get location of the pivot
	@add r4,r0,r1,LSL #2	
	mov r4,r1		@put the length of sub array into r4
	sub r4,r4,#1		@subtract 1 to get n-1

	mov r5,#0		@put 0 into r5 "J" counter
	mov r6,r5		@put J counter equal to I aka set I to 0

	b loop			@go to the loop
swap:
	add r7,r0,r4,LSL #2	@ get location of pivot
	ldr r7,[r7]		@ get value of the pivot

	add r8,r0,r5,LSL #2	@ get location of array[J]
	ldr r8,[r8]		@ get value of array [J]

	cmp r8,r7		@ compare the two 
	bgt increment		@ if no swap needed go to J+1

	add r7,r0,r6,LSL #2	@ get location of array at [I]	
	ldr r7,[r7]		@ get value of array at [I]

	add r9,r0,r6,LSL #2	@ get location of array at [I]
	str r8,[r9]		@ store value of J into array [I]

	add r8,r0,r5,LSL #2	@ get location of array at [J]
	str r7,[r8]		@ get store value of I into array[J]

	add r6,r6,#1		@ incrment the I counter
increment:
	add r5,r5,#1		@ incrment J counter
loop:				@ basicly a for loop
	cmp r4,r5		@ compare J counter to length -1
	bgt swap		@ if still in loop go to swap
	
	add r7,r0,r4,LSL #2	@ get location of pivot
	ldr r7,[r7]		@ get value of pivot
	add r8,r0,r6,LSL #2	@ get location of array[I]
	ldr r8,[r8]		@ get value of array[I]
	add r9,r0,r4,LSL #2	@ get location of pivot
	str r8,[r9]		@ store value of I into pivot location
	add r8,r0,r6,LSL #2	@ get location of array[I]
	str r7,[r8]		@ store pivot into array[I]

	mov r0,r6		@ put I into r0 for return
	@pop {r2-r9,fp}		@ pop registers but not lr
	b after			@ branch to after partion
quicksort:
	push {r2-r9,fp,lr}	@ first thing we do is push r2-r9 and fp,lr
	mov r2,r0		@ store location of start of array in r2
	mov r3,r1		@ store value of length into r3
	cmp r3,#1		@ compare r3 to 1 if less or equal done with sub
				@ array
	ble exit		@ branch to exit
	
	mov r0,r2		@ put starting location into r0
	mov r1,r3		@ put lenght ito r1
	b partition		@ go to partition
after:
	mov r9,r3		@ put orig length into r9
	mov r4,r0		@ put return value into r4
	mov r0,r2		@ put starting of array into r0
	mov r1,r4		@ put return value into r1
	bl quicksort		@ recurse the left side
	
	add r5,r4,#1		@ add one to return value and store
	add r6,r2,r5,LSL #2	@ get new starting location
	
	mov r5,r3		@ put orig length into r5 
	mov r7,r4		@ put return value into r7
	sub r7,r5,r7		@ subtract orig - return
	sub r7,r7,#1		@ subtact 1
	mov r0,r6		@ put new starting location into r0
	mov r1,r7		@ put new length into r1
	bl quicksort		@ recurse right side
exit:
	pop {r2-r9,fp,lr}	@pop registers and lr so we can move back up the recursion
	bx lr			@end
