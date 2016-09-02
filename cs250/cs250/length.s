.data
.balign 4
  greeting:
	.asciz "Enter a String: "  @ define the first print statment
.balign 4
  output: 
	.asciz "String: %s\n"      @ define the output statement
.balign 4
  scan_pattern:
	.asciz "%s"                @define the pattern to read
.balign 4
  string_read: .space 100                     @location to store string 

.balign 4
 outnum:				@output string
	.asciz "Num: %d\n"
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
	ldr r1,address_of_string_read        @load location to store word
	bl scanf                             @scan word in

	mov r5, #0				@put 0 into r5 to start count
	ldr r6,address_of_string_read		@put string we read into r6
	b loop					@enter a loop
loop:
	ldr r2,[r6]				@load first char into r2
	cmp r2,#0				@compare to null
	beq done				@if equal were done
	

	add r6,r6,#1				@add 1 to stirng pointer
	add r5,r5,#1				@add 1 to counter
	
	b loop					@start loop
done:	
       	#ldr r0, address_of_string_read       @load string we read to r0
	#ldr r0,[r0] 	                     @loads value of r0 into r0
	
	mov r1,r5				@move r5(length) into r1
	ldr r0, address_of_outnum		@load output string
	bl printf				@print
	
		

	ldr lr, address_of_return            @load return address to r1
	ldr lr, [lr]
	bx lr                                @end program

address_of_greeting: .word greeting
address_of_return: .word return
address_of_scan_pattern: .word scan_pattern
address_of_string_read: .word string_read
address_of_output: .word output
address_of_outnum: .word outnum
.global puts
.global printf
.global scanf
