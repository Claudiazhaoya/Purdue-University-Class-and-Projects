          .text
   .globl addarray
            .type   addarray, @function
     addarray:
              movq    $0,%rax
               movq    $0,%rdx
        
         setchar:
	          movl    (%rsi),%ecx
	          jmp     while
	  
	  while:
	          cmpq    %rdx,%rdi
	          jle     stopper
	  
	          addq    %rcx,%rax
	          addq    $4,%rsi
	          addq    $1,%rdx
	          jmp     setchar
	 stopper:              
