	.text
.globl add
	.type	add, @function
add:
	addq	%rsi, %rdi	#adds first two numbers
	movq	%rdi, %rax	#puts result into rax
	addq	%rdx, %rax	#adds third number
	ret			# returns added number
