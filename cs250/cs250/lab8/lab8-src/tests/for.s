 # Reserve space
	.data
i:
	.long 0
	.long 0

	.text
.globl main
main:
# Save registers
	pushq %rbx
	pushq %r10
	pushq %r13
	pushq %r14
	pushq %r15
	subq $128,%rsp
	#Save arguments

	# push 0
	movq $0,%rbx
	#Assign to Global var i
	movq %rbx, i(%rip)

	# FOR START
twirl_start_0:
	movq i,%rbx

	# push 15
	movq $15,%r10

	# less
	cmpq %r10,%rbx
movq $0, %r10
movq $0, %rbx
movq $1, %rdi
	cmovl %rdi, %rbx
	cmpq $0, %rbx
	jle twirl_end_0
	jmp for_assign_0
for_expression_0:
continue_point_0:
	movq i,%rbx

	# push 1
	movq $1,%r10

	# +
	addq %r10,%rbx
	#Assign to Global var i
	movq %rbx, i(%rip)
	jmp twirl_start_0
for_assign_0:
	#top=0

	# push string "i=%d\n" top=0
	movq $string0, %rbx
	movq i,%r10
     # func=printf nargs=2
     # Move values from reg stack to reg args
	movq %r10, %rsi
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx
	jmp for_expression_0
twirl_end_0:

	# FOR END
	#top=0

	# push string "OK\n" top=0
	movq $string1, %rbx
     # func=printf nargs=1
     # Move values from reg stack to reg args
	movq %rbx, %rdi
	movl    $0, %eax
	call printf
	movq %rax, %rbx
	addq $128,%rsp
# Restore registers
	popq %r15
	popq %r14
	popq %r13
	popq %r10
	popq %rbx
	ret
string0:
	.string "i=%d\n"

string1:
	.string "OK\n"

