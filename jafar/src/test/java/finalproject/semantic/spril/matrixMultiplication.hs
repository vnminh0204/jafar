import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 19) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 13) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Load (ImmValue 4) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 3) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regD 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (ImmValue 4) regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Load (ImmValue 6) regA 
    , Push regA 
    , Load (ImmValue 6) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 5) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 4) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 3) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regD 
    , Load (ImmValue 7) regA 
    , Push regA 
    , Load (ImmValue 10) regA 
    , Push regA 
    , Load (ImmValue 11) regA 
    , Push regA 
    , Load (ImmValue 20) regA 
    , Push regA 
    , Load (ImmValue 21) regA 
    , Push regA 
    , Load (ImmValue 30) regA 
    , Push regA 
    , Load (ImmValue 31) regA 
    , Push regA 
    , Load (ImmValue 6) regD 
    , Compute Add regD regSP regD 
    , Load (IndAddr regD) regD 
    , Pop regA 
    , Load (ImmValue 5) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 4) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 3) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regA 
    , Load (ImmValue 0) regB 
    , Compute Add regB regD regB 
    , Store regA (IndAddr regB) 
    , Pop regD 
    , Load (ImmValue 17) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 333) 
    , Nop 
    , Load (ImmValue 18) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 307) 
    , Nop 
    , Load (ImmValue 19) regA 
    , Push regA 
    , Load (ImmValue 0) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 281) 
    , Nop 
    , Load (ImmValue 0) regE 
    , Push regE 
    , Load (DirAddr 17) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 365) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 365) 
    , Pop regE 
    , Load (ImmValue 2) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (DirAddr 18) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 365) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 365) 
    , Pop regE 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 13) regA 
    , Pop regE 
    , Compute Add regA regE regE 
    , Push regE 
    , Load (ImmValue 0) regD 
    , Load (DirAddr 17) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 365) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 365) 
    , Load (ImmValue 2) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (DirAddr 18) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 365) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 365) 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (ImmValue 13) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Load (ImmValue 0) regD 
    , Load (DirAddr 17) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 365) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 365) 
    , Load (ImmValue 3) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (DirAddr 19) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 365) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 365) 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (ImmValue 1) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Load (ImmValue 0) regD 
    , Load (DirAddr 19) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 2) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 365) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 365) 
    , Load (ImmValue 2) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (DirAddr 18) regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Gt regA regB regB 
    , Branch regB (Abs 365) 
    , Compute Lt regA reg0 regB 
    , Branch regB (Abs 365) 
    , Load (ImmValue 1) regB 
    , Compute Mul regB regA regA 
    , Compute Add regA regD regD 
    , Load (ImmValue 7) regA 
    , Compute Add regA regD regD 
    , Load (IndAddr regD) regD 
    , Push regD 
    , Pop regB 
    , Pop regA 
    , Compute Mul regA regB regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue 19) regA 
    , Push regA 
    , Load (DirAddr 19) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Nop 
    , Load (DirAddr 19) regA 
    , Push regA 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 143) 
    , Load (ImmValue 18) regA 
    , Push regA 
    , Load (DirAddr 18) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Nop 
    , Load (DirAddr 18) regA 
    , Push regA 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 134) 
    , Load (ImmValue 17) regA 
    , Push regA 
    , Load (DirAddr 17) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Nop 
    , Load (DirAddr 17) regA 
    , Push regA 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Lt regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 125) 
    , Load (ImmValue 13) regA 
    , Push regA 
    , Pop regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , Load (IndAddr regA) regB 
    , WriteInstr regB numberIO 
    , Load (ImmValue 1) regB 
    , Compute Add regA regB regA 
    , EndProg 
    ]

main = run [prog0]

