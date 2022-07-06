import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 3) regF 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 60) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Load (ImmValue 36) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 59) 
    , Nop 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Load (DirAddr 2) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Gt regA regB regA 
    , Push regA 
    , Pop regA 
    , Compute Equal regA reg0 regA 
    , Branch regA (Abs 44) 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Load (DirAddr 2) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Sub regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 58) 
    , Nop 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Load (DirAddr 2) regA 
    , Push regA 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Sub regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Nop 
    , Nop 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Load (DirAddr 2) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute NEq regA regB regA 
    , Push regA 
    , Pop regA 
    , Load (ImmValue 1) regB 
    , Compute Equal regA regB regA 
    , Branch regA (Abs 18) 
    , Load (ImmValue 3) regA 
    , Push regA 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (DirAddr 3) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , EndProg 
    ]

main = run [prog0]

