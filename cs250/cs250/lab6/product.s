.data
.balign 4
  greeting:
	.asciz "Enter the numbers here\n"  @ define the first print statment
.balign 4
  error:
	.asciz "no numbers were entered\n" @error in case no numbers entered
.balign 4
  output: 
	.asciz "The product is: %d\n"  @output string
.balign 4
  scan_pattern:
	.asciz "%d"                @define the pattern to read
.balign 4
  nums: .skip 400		@set an array(dont use)
.balign 4
 count: .word 0 		@make a area for a count(num in)
.balign 4
  temp: .word 0			@have a temp to muliply with

.balign 4                          @for return value
 return: .word 0

.text
.global main
main:
	ldr r1, address_of_return             @load the return address into r1
	str lr, [r1]                          

	ldr r0, address_of_greeting           @load the first message to r0
	bl printf			      @ print first message
	mov r4,#0			@counter for number of nums in
	mov r5,#1			@put 1 into r5 to start the mulip
	
	ldr r1, address_of_nums 	@put the array into r1(not used)
	mov r2,#0			@put 0 into r2 for counter
inloop:
	
	ldr r0,address_of_scan_pattern       @load scan pattern
	ldr r1,address_of_temp        @load location to store num
	bl scanf                             @scan num

	ldr r9,address_of_temp		@put the num we read into r9
	ldr r9,[r9]			@put value of r9 into r9

	cmp r9,#-1			@compare r9 to -1
	beq done			@if equal we ar done
	mul r5,r9,r5			@multiply r5 to num we read in
	add r4,r4,#1			@add one to counter
	b inloop			@start loop over
warn:		
	ldr r0,address_of_error		@load error string
	bl printf			@print
	b exit				@exit
done:
	cmp r4,#0			@check if number of numbers enterd is0
	beq warn			@if so print error

	mov r1,r5			@put num calc into r1
	ldr r0, address_of_output	@load output string
	bl printf			@print
	b exit				@exit
	
		
exit:
	ldr lr, address_of_return            @load return address to r1
	ldr lr, [lr]
	bx lr                                @end program

address_of_greeting: .word greeting
address_of_return: .word return
address_of_scan_pattern: .word scan_pattern
address_of_output: .word output
address_of_nums: .word nums
address_of_temp: .word temp
address_of_count: .word count
address_of_error: .word error
.global puts
.global printf
.global scanf
