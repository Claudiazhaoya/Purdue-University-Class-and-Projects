.data
.balign 4
  greeting:
	.asciz "Enter nth number to find\n"  @ define the first print statment
.balign 4
  error:
	.asciz "no numbers were entered\n"   @define error statment
.balign 4
  output: 
	.asciz "%d\n"			  @set the output string
.balign 4
  scan_pattern:
	.asciz "%d"                @define the pattern to read
.balign 4
 count: .word 0 		@a counter
.balign 4
  nthnum: .word 0		@the value we bring in
.balign 4                          @for return value
 return: .word 0

.text
.global main
main:
	ldr r1, address_of_return             @load the return address into r1
	str lr, [r1]                          

	ldr r0, address_of_greeting           @load the first message to r0
	bl printf			      @ print first message
	
	
	ldr r0,address_of_scan_pattern       @load scan pattern
	ldr r1,address_of_nthnum        @load location to the nth number
	bl scanf                             @scan number in

	ldr r2,address_of_nthnum	@put the nth num in r2
	ldr r2,[r2]			@load the value of r2 into r2
	
	mov r3,#1 	@counter	@put a 1 in r3 for  a counter
	mov r4,#0	@temp1		@put 0 in r4 for start of sequence
	mov r5,#1	@temp2		@put 1 in r5 for second num in seq
inloop:

	cmp r3,r2			@compare r3(counter) to r2(num in)
	beq done			@if equal we are done
	
	add r5,r4,r5			@add r4 and r5 place in r5
	sub r4,r5,r4			@sub r4 from r5 and place in r4
	add r3,r3,#1			@add 1 to the counter
	b inloop			@start loop over
done:

	mov r1,r5			@put r5(value we found) into r1
	ldr r0, address_of_output	@load the output string into r0
	bl printf			@pritn
	b exit				@exit
	
		
exit:
	ldr lr, address_of_return            @load return address to r1
	ldr lr, [lr]
	bx lr                                @end program

address_of_greeting: .word greeting
address_of_return: .word return
address_of_scan_pattern: .word scan_pattern
address_of_output: .word output
address_of_nthnum: .word nthnum
address_of_count: .word count
address_of_error: .word error
.global puts
.global printf
.global scanf
