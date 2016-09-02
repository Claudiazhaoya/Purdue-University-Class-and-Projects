.global bubble
bubble:
	push {r4-r9,fp,lr}
	mov fp,sp
	sub sp,sp,#20   @make space for the five ints i need

	mov r4,r1       @get r1 or the number of ints in the array
	sub r4,r4,#1
	mov r5,#1       @this will act as a boolean 0 means no swap 1 mean swap
	mov r6,#0       @this is the counter for the inner loop

loop:
	cmp r5,#0
	beq done
	mov r5,#0
	mov r6,#0
	b step1

step1:
	cmp r6,r4
	beq loop
	add r7,r0,r6,LSL #2   @location of a[n]
	ldr r8,[r7]           @value of a[n]
	add r9,r7,#4	      @location of a[n+1]
	ldr r9,[r9]           @value of a[n+1]
	add r6,r6,#1
	
	cmp r9,r8
	ble  step2
	b step1
step2:
	str r9,[r7]
	add r9,r7,#4
	str r8,[r9]
	mov r5,#1
	b step1
done:
	mov sp,fp
	pop {r4-r9,fp,lr}
	bx lr
