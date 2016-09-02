	.text
.globl fact
	.type	fact, @function
fact:
	movq	$1,%rsi			#puts 1 into rsi
	movq	%rdi,%rdx		#puts rdi into rdx
	cvtsi2sd	%rsi,%xmm0	# type cast rsi into double
	cvtsi2sd	%rsi,%xmm2	#type cast rsi into double
	cvtsi2sd	%rdi,%xmm1	#type cast rdi into double
	jmp	factor			# go to factor function
setnum:
	subq	$1,%rdi			#subtracts one from rdi
	subsd	%xmm2,%xmm1		#subtracts xmm2 from xmm1
	jmp	factor			#jump to factor funtion

factor:
	cmpq	$0,%rdi		# compares rdi to zero
	jle	stop		# if it is fo to stop

	mulsd	%xmm1,%xmm0	# mutliplty xmm1 and xmm0
	jmp	setnum		#jump to setnum function

stop:
	cmpq	$0,%rdx		#compares 0 to rdx
	jle	zero		#if zero goes to return

	ret
zero:
	cvtsi2sd	%rdx,%xmm0	#return function
	ret
