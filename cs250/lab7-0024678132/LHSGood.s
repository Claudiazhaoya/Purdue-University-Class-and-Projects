.global quicksort
partition:
	@add r4,r0,r1,LSL #2
	mov r4,r1
	sub r4,r4,#1

	mov r5,#0
	mov r6,r5

	b loop
swap:
	add r7,r0,r4,LSL #2
	ldr r7,[r7]

	add r8,r0,r5,LSL #2
	ldr r8,[r8]

	cmp r8,r7
	bgt increment

	add r7,r0,r6,LSL #2
	ldr r7,[r7]

	add r9,r0,r6,LSL #2
	str r8,[r9]

	add r8,r0,r5,LSL #2
	str r7,[r8]

	add r6,r6,#1
increment:
	add r5,r5,#1		@incrment J counter
loop:
	cmp r4,r5
	bgt swap
	
	add r7,r0,r4,LSL #2
	ldr r7,[r7]
	add r8,r0,r6,LSL #2
	ldr r8,[r8]
	add r9,r0,r4,LSL #2
	str r8,[r9]
	add r8,r0,r6,LSL #2
	str r7,[r8]

	mov r0,r6
	@pop {r2-r9,fp}
	b after
quicksort:
	push {r2-r9,fp,lr}
	mov r2,r0
	mov r3,r1
	cmp r3,#1
	ble exit
	
	mov r0,r2
	mov r1,r3
	b partition
after:
	mov r9,r3
	mov r4,r0
	mov r0,r2
	mov r1,r4
	bl quicksort
	
	@mov r4,r9
	@mov r5,r4
	@add r5,r5,#1
	@add r6,r0,r5,LSL #2
	
	@mov r5,r2
	@mov r7,r4
	@sub r7,r5,r7
	@sub r7,r7,#1
	@mov r0,r6
	@mov r1,r7
	@bl quicksort
exit:
	pop {r2-r9,fp,lr}	@final pop before finish
	bx lr			@end
