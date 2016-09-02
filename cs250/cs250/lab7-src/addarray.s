	.text
.globl addarray
	.type	addarray, @function
addarray:
	movq	$0,%rax		#puts zero in rax
	movq	$0,%rdx		#puts zero in rdx

setcha:
	movl	(%rsi),%ecx	#sets integer of current element to ecx
	jmp	while		# go to while function

while:
	cmpq    %rdx,%rdi	#compares for stopping 
	jle	stopper		#goes to stopp funtion

	addq	%rcx,%rax	#addes numbers togeter
	addq	$4,%rsi		#incremtnsts pointer by four bytes
	addq	$1,%rdx
	jmp	setchar		#go to set char function
stopper:	
	ret			#return fuction
