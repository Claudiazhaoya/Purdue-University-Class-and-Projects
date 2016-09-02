	.file	"mystrlen.c"
	.text
.globl mystrlen
	.type	mystrlen, @function
mystrlen:
	movq	$0,%rdx		#sets rdx to zero
setchar:
	movb	(%rdi),%cl	#sets the char for a compare
	jmp	while

while:
	cmpb	$0,%cl		# compares char to null terminator
	jle	stopper		# if it is null go to stopper function

	addq	$1,%rdx		#addes one to the counter
	addq	$1,%rdi		#addes one to incrment the aray 
	jmp	setchar		# go to set char function
stopper:  
	movq	%rdx,%rax	# puts counter into rax
	ret			#returns
